using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerControls : MonoBehaviour {

	[Header("values")]
    public float speed = 5;
	public bool canControl = true;
	public enum facingDir { left,right,up,down};
	public facingDir facing;
	public bool awake = false;

	Rigidbody2D rb;
	[Header("References")]
	public Animator anim;
	public SpriteRenderer mySprite;
	public Collider2D leftAtk,rightAtk,upAtk,downAtk;
	public GameObject normalShadow, sleepShadow;
    // Use this for initialization
	void Awake () {
        rb = gameObject.GetComponent<Rigidbody2D>();
		MCP.player = gameObject;
	}
	
	// Update is called once per frame
	void Update () {
		if(awake)
			anim.SetBool("Awake", true);
		if (canControl)
		{
			MoveManager();
			AnimManager();
			if(Input.GetButtonDown("Swing"))
				StartCoroutine(Attack());
		}
		else
		{
			rb.velocity = Vector3.zero;
		}

	}
	public void StartWake(float wakeTime)
	{
		StartCoroutine(Wake(wakeTime));
	}
	public IEnumerator Wake(float wakeTime)
	{
		anim.SetTrigger("Wake");
		awake = true;
		yield return new WaitForSeconds(wakeTime);
		canControl = true;
		sleepShadow.SetActive(false);
		normalShadow.SetActive(true);

	}

	IEnumerator Attack()
	{
		anim.SetTrigger("Swing");
		canControl = false;
		yield return new WaitForSeconds(0.25f);
		if (facing == facingDir.left)
			leftAtk.gameObject.SetActive(true);
		else if (facing == facingDir.right)
			rightAtk.gameObject.SetActive(true);
		if (facing == facingDir.down)
			downAtk.gameObject.SetActive(true);
		if (facing == facingDir.up)
			upAtk.gameObject.SetActive(true);
		yield return new WaitForSeconds(0.25f);
		canControl = true;
		upAtk.gameObject.SetActive(false);
		downAtk.gameObject.SetActive(false);
		rightAtk.gameObject.SetActive(false);
		leftAtk.gameObject.SetActive(false);
	}

	void MoveManager()
	{
        float v = speed;
		if (Math.Abs(Input.GetAxis("Horizontal")) == 1 && Math.Abs(Input.GetAxis("Vertical")) == 1)
		{
			v = speed * 0.75f;
		}
		else if (Math.Abs(Input.GetAxis("Horizontal")) == 0 && Math.Abs(Input.GetAxis("Vertical")) == 0)
		{
			v = speed;
		}
        if(CameraControls.isTransitioning)
        {
            rb.velocity = Vector3.zero;
        }
		rb.velocity = new Vector3(Input.GetAxis("Horizontal") * v, Input.GetAxis("Vertical") * v, 0);

	}

	void AnimManager()
	{
		anim.SetFloat("X", Mathf.Abs(Input.GetAxis("Horizontal")));
		anim.SetFloat("Y", Input.GetAxis("Vertical"));
		if(facing == facingDir.left)
			mySprite.flipX = true;
		else
			mySprite.flipX = false;

		if(Input.GetAxis("Horizontal") < 0 && Mathf.Abs(Input.GetAxis("Horizontal")) > Mathf.Abs(Input.GetAxis("Vertical")))
			facing = facingDir.left;
		if (Input.GetAxis("Horizontal") > 0 && Mathf.Abs(Input.GetAxis("Horizontal")) > Mathf.Abs(Input.GetAxis("Vertical")))
			facing = facingDir.right;
		if (Input.GetAxis("Vertical") < 0 && Mathf.Abs(Input.GetAxis("Vertical")) > Mathf.Abs(Input.GetAxis("Horizontal")))
			facing = facingDir.down;
		if (Input.GetAxis("Vertical") > 0 && Mathf.Abs(Input.GetAxis("Vertical")) > Mathf.Abs(Input.GetAxis("Horizontal")))
			facing = facingDir.up;
		anim.SetInteger("facing", (int)facing);
	}
}
