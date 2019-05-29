using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player_Move : MonoBehaviour {

	public int playerSpeed = 10;
	public int playerJumpPower = 1250;
	private float moveX;
    public bool isGrounded;
    public float distanceToBottomOfPlayer = 0.95f;
    RaycastHit2D rayDown;

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		PlayerMove ();
        PlayerRaycast();
        if(rayDown.distance > distanceToBottomOfPlayer)
        {
            isGrounded = false;
        }
	}

	void PlayerMove () {
		moveX = Input.GetAxis("Horizontal");
		if (Input.GetButtonDown ("Jump") && isGrounded) {
			Jump ();
		}

        if(moveX > -0.2f && moveX < 0.2f)
        {
            GetComponent<Animator>().SetBool("isRunning", false);
        }
        else
        {
            GetComponent<Animator>().SetBool("isRunning", true);
        }

        if (moveX < 0.0f ) {
			GetComponent<SpriteRenderer> ().flipX = true;
		} else if (moveX > 0.0f) {
			GetComponent<SpriteRenderer> ().flipX = false;
		}

		gameObject.GetComponent<Rigidbody2D> ().velocity = new Vector2 (moveX * playerSpeed, gameObject.GetComponent<Rigidbody2D> ().velocity.y);
	}

	void Jump () {    
        isGrounded = false;    
		GetComponent<Rigidbody2D> ().AddForce (Vector2.up * playerJumpPower);
	}


    void OnCollisionEnter2D(Collision2D col)
    {


    }

    void PlayerRaycast()
    {
        RaycastHit2D rayUp = Physics2D.Raycast(transform.position, Vector2.up);
        if (rayUp != null && rayUp.collider != null && rayUp.distance < distanceToBottomOfPlayer && rayUp.collider.name == "boxItem")
        {
            Destroy(rayUp.collider.gameObject);
        }

        rayDown = Physics2D.Raycast(transform.position, Vector2.down);
        if(rayDown != null && rayDown.collider != null && rayDown.distance<distanceToBottomOfPlayer && rayDown.collider.tag == "enemy")
        {
            GetComponent<Rigidbody2D>().AddForce(Vector2.up * 1000);
            rayDown.collider.gameObject.GetComponent<Rigidbody2D>().AddForce(Vector2.right * 100);
            rayDown.collider.gameObject.GetComponent<Rigidbody2D>().AddForce(Vector2.down * 300);
            rayDown.collider.gameObject.GetComponent<Rigidbody2D>().freezeRotation = false;

            rayDown.collider.gameObject.GetComponent<BoxCollider2D>().enabled = false;
            rayDown.collider.gameObject.GetComponent<EnemyMove>().enabled = false;
        }
        if (rayDown != null && rayDown.collider != null && rayDown.distance < distanceToBottomOfPlayer && rayDown.collider.tag != "enemy")
        {
            isGrounded = true;
        }
    }
}
